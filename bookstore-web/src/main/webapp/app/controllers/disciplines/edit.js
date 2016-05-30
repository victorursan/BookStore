import Ember from 'ember';

export default Ember.Controller.extend({

  actions: {

    onUpdateButtonClick(discipline) {

      var self = this;

      function transitionToDisciplines() {
        console.log('transition to route...');
        self.transitionToRoute('disciplines');
      }

      function failure(reason) {
        console.log(reason);
      }

      console.log(discipline);
      discipline.save().then(transitionToDisciplines).catch(failure);
    },

    onInputBlur(event){
      var discipline = this.get('model.discipline');
      var property = event.target.name;
      var value = event.target.value;
      discipline.set(property, value);
    },

    onGradeInputBlur(studentDiscipline, event){
      studentDiscipline.set('grade', event.target.value);
      console.log('saving grade... ', studentDiscipline.get('grade'));
      studentDiscipline.save();
    },

  }

});
