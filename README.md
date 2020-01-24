# How to use

* Audit the code, reverse the binary (seriously, don't just trust me)
* Go to the website that manages 2FA
  * e.g. not-start-portal, not-some-university portals
* Add a new phone
  * Input your phone number and those kinds of stuff
  * Just choose Android (you are gonna use different 2FA client anyway)
  * Do not use the QR code. Choose the option which sends the activation
    link to your email
  * The continue button is disabled, for now)
  * You should see the link of the form
    `https://m-xxxxxxxx.duosecurity.com/android/XXXXXXXXXXXXXXXXXXXX`
    * If you don't, just click on the link and check the url bar
    * Copy it
* Run the application (in the `bin` directory)
  * Paste the link in
  * Wait
  * Click continue on the web page (not it should be highlighted)
  * Copy the hopt secret and use it with whatever authenticator you
    want. (Note: Duo uses *counter based* hopt)
    * Example: you can use
      [this extension](https://github.com/Authenticator-Extension/Authenticator)
      in browser
      * Edit -> Manual Entry
      * Counter based; name = any thing; secret is the hopt you get from
        the app
      * Done. Now you can get your passcode right in your browser

# Is this secure?

No. Just like the Duo App. (you should not use the browser extension by the way; but if you do remember to set the password or encrypt it)

# Alternative (preferred) methods

Duo allows using FIDO devices to authenticate (that is actually more
secure than using phones tbh). It also supports TouchID (only Apple and Chrome).
However, ~~i am broke~~ another solution supporting all platforms is using a
u2f emulation -> add u2f devices to Duo

* Linux: https://github.com/danstiner/rust-u2f (remember to set udev rules depending on your distro)
* Win: https://github.com/SoftU2F/SoftU2F-Win
* Mac: https://github.com/github/SoftU2F

[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=C44YKYMVNL4TA)