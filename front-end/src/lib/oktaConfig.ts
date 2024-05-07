export const oktaConfig = {
    clientId: '0oag52t6bedjNvnks5d7',
    issuer: 'https://dev-10065219.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}