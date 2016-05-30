import Ember from 'ember';

export default Ember.Component.extend({
  actions: {
    onDeleteButtonClick(clientId){
      console.log("delete button clicked...", clientId);

      this.get('onDelete')(clientId);

    }
  }
});
