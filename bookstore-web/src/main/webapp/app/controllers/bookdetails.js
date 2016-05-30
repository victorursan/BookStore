import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {

    onUpdateButtonClick(book) {

      var self = this;

      function transitionToBooks() {
        console.log('transition to route...');
        self.transitionToRoute('books');
      }

      function failure(reason) {
        console.log(reason);
      }

      book.save().then(transitionToBooks).catch(failure);
    },
    onInputBlur(event){
      var book = this.get('model.book');
      var property = event.target.name;
      var value = event.target.value;
      book.set(property, value);
    }

  }
});
