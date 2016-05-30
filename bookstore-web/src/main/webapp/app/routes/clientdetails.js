import Ember from 'ember';

export default Ember.Route.extend({
  model(params) {
    return Ember.RSVP.hash({
      client: this.store.peekRecord('client',params.client_id),
      books: this.store.findAll('book')
    });
  }
});
