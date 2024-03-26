export const oktaConfig = {
    clientId: '0oag06r64dtzz9B895d7',
    issuer: 'https://dev-15821166.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}
