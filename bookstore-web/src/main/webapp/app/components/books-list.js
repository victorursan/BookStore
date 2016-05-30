import Ember from 'ember';

export default Ember.Component.extend({
  actions: {
    onDeleteButtonClick(bookId){
      console.log("delete button clicked...", bookId);

      this.get('onDelete')(bookId);

    }
  }
});
