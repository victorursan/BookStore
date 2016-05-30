import Ember from 'ember';

export default Ember.Component.extend({
  actions: {
    onDeleteButtonClick(studentId){
      console.log("delete button clicked...", studentId);

      this.get('onDelete')(studentId);

    }
  }
});
