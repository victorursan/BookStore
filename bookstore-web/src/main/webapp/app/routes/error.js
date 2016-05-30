import Ember from 'ember';

export default Ember.Route.extend({
  model(params, transition){
    console.log(transition.queryParams, transition.queryParams.message);
    return transition.queryParams;
  }
});
