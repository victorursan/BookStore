import Ember from 'ember';
import OAuth2PasswordGrant from 'ember-simple-auth/authenticators/oauth2-password-grant';

export default OAuth2PasswordGrant.extend({
  serverTokenEndpoint: '/stcatalog/j_spring_security_check',
  serverTokenRevocationEndpoint: '/stcatalog/j_spring_security_logout',

  makeRequest: function (url, data) {
    const options = {
      url,
      data,
      type: 'POST',
      contentType: 'application/x-www-form-urlencoded'
    };

    return Ember.$.ajax(options);
  },

  authenticate: function (identification, password, scope = []) {
    return new Ember.RSVP.Promise((resolve, reject) => {
      const data = {j_username: identification, j_password: password};
      const serverTokenEndpoint = this.get('serverTokenEndpoint');
      
      this.makeRequest(serverTokenEndpoint, data).done(function (data, status, jqXHR) {
        Ember.run(function () {
          resolve({access_token: status});
        });
      }).fail(function (jqXHR, status, errorThrown) {
        console.error('failed to authenticate user. status: ' + status + " error: " + errorThrown);
        if (jqXHR.status === 401) {
          var errorMsg = "Incorrect e-mail or password";
          reject(errorMsg);
        } else {
          reject(status);
        }
      }).always(function () {
//do nothing
      });
    });
  },

});
