import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {

    deleteClient(clientId){
      console.log("deleteClient ", clientId);

      var client = this.store.peekRecord('client', clientId);
      this.store.deleteRecord(client);
      client.save();

      this.transitionToRoute('clients');
    }

  }
});
