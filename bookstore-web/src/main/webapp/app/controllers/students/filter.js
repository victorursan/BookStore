import Ember from 'ember';

export default Ember.Controller.extend({
  ajax: Ember.inject.service(),
  actions: {

    onFilterButtonClick() {

      var self = this;

      var name = this.get('name');

      this.get('ajax').request('/stcatalog/api/students/filter', {data: {name: name}}).then(function (students) {
          console.log("filtered students: ", students);

          self.store.unloadAll();
          self.store.pushPayload('student', students);

          self.transitionToRoute('students');
        },
        function (reason) {
          console.log("error filtering students ", reason);
        });

      this.setProperties({
        name: '',
      });

    }
  }
});
