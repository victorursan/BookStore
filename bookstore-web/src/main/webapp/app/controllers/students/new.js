import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {

    onAddButtonClick() {

      var self = this;

      function transitionToStudents() {
        self.transitionToRoute('students');
      }

      function failure(reason) {
        console.log(reason);
      }

      var serialNumber = this.get('serialNumber');
      var name = this.get('name');
      var groupNumber = this.get('groupNumber');

      var student = this.store.createRecord('student', {
        serialNumber: serialNumber,
        name: name,
        groupNumber: groupNumber
      });

      student.save().then(transitionToStudents).catch(failure);
      
      this.setProperties({
        serialNumber: '',
        name: '',
        groupNumber: ''
      });

    }
  }

});
