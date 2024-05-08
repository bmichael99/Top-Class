export const oktaConfig = {
    clientId: '0oaglhma4gK5fBlmv5d7',
    issuer: 'https://dev-83589524.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}