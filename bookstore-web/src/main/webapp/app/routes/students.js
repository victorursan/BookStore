import Ember from 'ember';


export default Ember.Route.extend({
  access: ['ADMIN', 'STUDENT'],
  session: Ember.inject.service('session'),

  beforeModel: function (transition) {
    var currentUser = this.get('session.data.currentUser');
    if (!this.get('access').contains(currentUser.role)) {
      console.log("user not authenticated or invalid role: ", currentUser);
      this.transitionTo('error',
        {queryParams: {message: 'user not authenticated or invalid role: ' + JSON.stringify(currentUser)}});
    }
    return true;
  },

  model() {
    // return this.store.findAll('student');
    return Ember.RSVP.hash({
      students: this.store.findAll('student'),
      // disciplines: this.store.findAll('discipline'),
    });
  },

  actions: {
    error: function (err) {
      this.transitionTo('error', {queryParams: {message: err}});
    }
  }

});
