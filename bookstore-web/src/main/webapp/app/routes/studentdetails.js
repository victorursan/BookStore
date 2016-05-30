import Ember from 'ember';

export default Ember.Route.extend({
 model(params) {
    // return this.store.peekRecord('student',params.student_id);
   return Ember.RSVP.hash({
     student: this.store.peekRecord('student',params.student_id),
     disciplines: this.store.findAll('discipline'),
   });
  }
});


