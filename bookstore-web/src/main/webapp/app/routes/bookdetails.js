import Ember from 'ember';

export default Ember.Route.extend({
  model(params) {
    return this.store.peekRecord('book',params.book_id);
  }
});
