import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import org.apache.commons.codec.binary.Base32

private fun getSecret(url: String): String {
    val parts = url.split('/')
    val slug = parts.last()
    val host = parts[2]
    val id = host.split('.').first().split('-').last() // regex is overrated anyway

    val mapper = jacksonObjectMapper()
    val endpoint = "https://api-$id.duosecurity.com/push/v2/activation/$slug"
    val (_, response, result) = ("$endpoint?customer_parameter=1").httpPost(
        listOf(
            "touchid_status" to "not_supported",
            "jailbroken" to "false",
            "architecture" to "arch64",
            "region" to "US",
            "app_id" to "com.duosecurity.duomobile",
            "full_disk_encryption" to "true",
            "passcode_status" to "true",
            "platform" to "Android",
            "version" to "9.0.0",
            "manufacturer" to "Samsung",
            "language" to "en",
            "model" to "Samsung Galaxy C9 Pro",
            "security_patch_level" to "2020-01-01"
        )
    ).header("User-Agent" to "okhttp/4.0.0").responseString()
    return when (result) {
        is Result.Failure -> {
            if (response.statusCode == 404) {
                println("You already used this url before or the url is incorrect")
            }
            throw result.getException()
        }
        is Result.Success -> mapper.readTree(result.get()).at("/response/hotp_secret").textValue()
    }
}

fun main() {
    println("Input the url")
    println("Should be something like https://m-xxxxxxxx.duosecurity.com/android/XXXXXXXXXXXXXXXXXXXX")
    print(">")
    val url = readLine()!!.trim()
    try {
        val secret = Base32().encodeToString(getSecret(url).toByteArray())

        println("Your hotp secret is: $secret")
        println("======")
        println("Now use it with your favorite authenticator! You are on your own btw")
    } catch (e: Exception) {
        println(e.message)
    }
    println("press Enter to exit")
    readLine()
}