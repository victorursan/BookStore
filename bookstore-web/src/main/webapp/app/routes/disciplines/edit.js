import Ember from 'ember';

export default Ember.Route.extend({
  model(params){
    return Ember.RSVP.hash({
      discipline: this.store.peekRecord('discipline', params.discipline_id),
      // students: this.store.findAll('student'),
      studentDisciplines: this.store.query('studentDiscipline', {disciplineId: params.discipline_id}),
    });
  },

  actions: {
    error: function (err) {
      this.transitionTo('error', {queryParams: {message: err}});
    }
  }

});
