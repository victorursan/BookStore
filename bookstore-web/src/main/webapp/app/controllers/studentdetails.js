import Ember from 'ember';

export default Ember.Controller.extend({
  
  
  actions: {

    onUpdateButtonClick(student) {

      var self = this;

      function transitionToStudents() {
        console.log('transition to route...');
        self.transitionToRoute('students');
      }

      function failure(reason) {
        console.log(reason);
      }

      // console.log(student.get('name'));
      console.log(student);
      student.save().then(transitionToStudents).catch(failure);
    },

    onInputBlur(event){
      var student = this.get('model.student');
      var property = event.target.name;
      var value = event.target.value;
      student.set(property, value);
    },

    onAddDisciplineButtonClick(){
      var availableDisciplinesElement = document.getElementById('availableDisciplines');
      var disciplineId = availableDisciplinesElement.options[availableDisciplinesElement.selectedIndex].value;

      var discipline = this.store.peekRecord('discipline', disciplineId);
      var selectedDisciplines = this.get('model.student.disciplines');
      selectedDisciplines.pushObject(discipline);
      
    },
  }

});
