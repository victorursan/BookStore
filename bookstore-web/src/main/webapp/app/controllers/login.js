import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  ajax: Ember.inject.service(),

  actions: {
    authenticate() {
      var self = this;

      let {identification, password} = this.getProperties('identification', 'password');

      this.get('session').authenticate('authenticator:oauth2', identification, password)
        .then((param) => {
          console.log("paramas: ", param);
          self.get('ajax').request('/bookstore/api/users').then(function (user) {
              self.get('session').set('data.currentUser', user);
              console.log("current user set to session: ", self.get('session.data.currentUser'));
            },
            function (reason) {
              console.log("error getting user ", reason);
            });
        })
        .catch((reason) => {
          this.set('errorMessage', reason.error || reason);
        });
    }
  }
});
