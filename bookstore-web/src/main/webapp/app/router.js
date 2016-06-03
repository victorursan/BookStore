import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('clients', function() {
    this.route('newclient');
  });
  this.route('books', function() {
    this.route('newbook');
  });
  this.route('clientdetails', {path: '/clientdetails/:client_id'});
  this.route('bookdetails', {path: '/bookdetails/:book_id'});
  this.route('bookstore');
  this.route('login');
});

export default Router;

