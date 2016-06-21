import Ember from 'ember';

export default Ember.Route.extend({
  model(params) {
    return Ember.RSVP.hash({
      author: this.store.peekRecord('author',params.author_id),
      books: this.store.findAll('book')
    });
  }
});
