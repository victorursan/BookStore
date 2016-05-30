import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {

    onAddButtonClick() {

      var self = this;

      function transitionToClients() {
        self.transitionToRoute('clients');
      }

      function failure(reason) {
        console.log(reason);
      }

      var firstName = this.get('firstName');
      var lastName = this.get('lastName');

      var client = this.store.createRecord('client', {
        firstName: firstName,
        lastName: lastName
      });

      client.save().then(transitionToClients).catch(failure);

      this.setProperties({
        firstName: '',
        lastName: ''
      });

    }
  }

});
