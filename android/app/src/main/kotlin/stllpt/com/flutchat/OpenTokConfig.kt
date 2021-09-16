package stllpt.com.flutchat

import android.webkit.URLUtil

object OpenTokConfig {
    // *** Fill the following variables using your own Project info from the OpenTok dashboard  ***
    // ***                      https://dashboard.tokbox.com/projects                           ***

    // Replace with your OpenTok API key
    val API_KEY = "47324141"
    // Replace with a generated Session ID
    val SESSION_ID = "2_MX40NzMyNDE0MX5-MTYzMTAwNzAzNDg2NH5EbDJjand5bFhrT2pyVmtiU1NDekxpeE9-fg"
    // Replace with a generated token (from the dashboard or using an OpenTok server SDK)
    val TOKEN = "T1==cGFydG5lcl9pZD00NzMyNDE0MSZzaWc9MTMyOWM5NGRmYWE5ZmI4MGYxYzYyOThmMTU2MWViMGE1N2Y2YTZjNzpzZXNzaW9uX2lkPTJfTVg0ME56TXlOREUwTVg1LU1UWXpNVEF3TnpBek5EZzJOSDVFYkRKamFuZDViRmhyVDJweVZtdGlVMU5EZWt4cGVFOS1mZyZjcmVhdGVfdGltZT0xNjMxMDA3MDM1Jm5vbmNlPTAuNjQwNTA3NTgyNjY0MjM1JnJvbGU9cHVibGlzaGVyJmV4cGlyZV90aW1lPTE2MzEwMDg4MzUmaW5pdGlhbF9sYXlvdXRfY2xhc3NfbGlzdD0="
    /*                           ***** OPTIONAL *****
     If you have set up a server to provide session information replace the null value
     in CHAT_SERVER_URL with it.

     For example: "https://yoursubdomain.com"
    */
    val CHAT_SERVER_URL: String? = null
    val SESSION_INFO_ENDPOINT = CHAT_SERVER_URL?:"" + "/session"


    // *** The code below is to validate this configuration file. You do not need to modify it  ***

    lateinit var webServerConfigErrorMessage: String
    lateinit var hardCodedConfigErrorMessage: String

    val isWebServerConfigUrlValid: Boolean
        get() {
            if (OpenTokConfig.CHAT_SERVER_URL == null || OpenTokConfig.CHAT_SERVER_URL.isEmpty()) {
                webServerConfigErrorMessage = "CHAT_SERVER_URL in OpenTokConfig.java must not be null or empty"
                return false
            } else if (!(URLUtil.isHttpsUrl(OpenTokConfig.CHAT_SERVER_URL) || URLUtil.isHttpUrl(OpenTokConfig.CHAT_SERVER_URL))) {
                webServerConfigErrorMessage = "CHAT_SERVER_URL in OpenTokConfig.java must be specified as either http or https"
                return false
            } else if (!URLUtil.isValidUrl(OpenTokConfig.CHAT_SERVER_URL)) {
                webServerConfigErrorMessage = "CHAT_SERVER_URL in OpenTokConfig.java is not a valid URL"
                return false
            } else {
                return true
            }
        }

    fun areHardCodedConfigsValid(): Boolean {
        if (OpenTokConfig.API_KEY != null && !OpenTokConfig.API_KEY.isEmpty()
                && OpenTokConfig.SESSION_ID != null && !OpenTokConfig.SESSION_ID.isEmpty()
                && OpenTokConfig.TOKEN != null && !OpenTokConfig.TOKEN.isEmpty()) {
            return true
        } else {
            hardCodedConfigErrorMessage = "API KEY, SESSION ID and TOKEN in OpenTokConfig.java cannot be null or empty."
            return false
        }
    }
}
