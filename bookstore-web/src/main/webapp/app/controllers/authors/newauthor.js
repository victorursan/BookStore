import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    onAddButtonClick() {

      var self = this;

      function transitionToAuthors() {
        self.transitionToRoute('authors');
      }

      function failure(reason) {
        console.log(reason);
      }

      var name = this.get('name');

      var author = this.store.createRecord('author', {
        name: name
      });

      author.save().then(transitionToAuthors).catch(failure);

      this.setProperties({
        name: ''
      });

    }
  }
});
