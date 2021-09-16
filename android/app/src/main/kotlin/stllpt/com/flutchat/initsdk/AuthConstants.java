package stllpt.com.flutchat.initsdk;

public interface AuthConstants {

	// TODO Change it to your web domain
	public final static String WEB_DOMAIN = "zoom.us";

	/**
	 * We recommend that, you can generate jwttoken on your own server instead of hardcore in the code.
	 * We hardcore it here, just to run the demo.
	 *
	 * You can generate a jwttoken on the https://jwt.io/
	 * with this payload:
	 * {
	 *
	 *     "appKey": "string", // app key
	 *     "iat": long, // access token issue timestamp
	 *     "exp": long, // access token expire time
	 *     "tokenExp": long // token expire time
	 * }
	 */
//		public final static String SDK_JWTTOKEN = "jwt_token";

	public final static String API_KEY = "wNSLYyfFeNm5Tx8HQmkpQAnbH1DrOaYpZyPo";
	public final static String APP_SECRET = "N2uPYUmGI4F7V8imgR8lVAF2S1PB3MrRQpYL";
}
