import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin);

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  isAdmin: Ember.computed('session', function () {
    return this.get('session.data.currentUser.role') === 'ADMIN';
  }),

  actions: {

    deleteStudent(studentId){
      console.log("deleteStudent ", studentId);

      var student = this.store.peekRecord('student', studentId);
      this.store.deleteRecord(student);
      student.save();

      this.transitionToRoute('students');
    },
  }
});
