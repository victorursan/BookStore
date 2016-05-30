import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function () {
  this.route('students', function () {
    this.route('new');
    this.route('filter');
  });
  // this.route('disciplines', function () {
  //   this.route('edit', {path: '/edit/:discipline_id'});
  // });
  this.route('disciplines');
  this.route('disciplines.edit',{path: '/disciplines/edit/:discipline_id'});
  this.route('studentdetails', {path: '/studentdetails/:student_id'});
  this.route('error', {queryParams: ['message']});
  this.route('login');
});

export default Router;
