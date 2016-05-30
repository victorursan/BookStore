import DS from 'ember-data';
// import Ember from 'ember';

export default DS.Model.extend({
  serialNumber: DS.attr(),
  name: DS.attr(),
  groupNumber: DS.attr(),
  disciplines: DS.hasMany('discipline')
  // studentDisciplines: DS.hasMany('studentDiscipline') 

  // toString: Ember.computed('serialNumber', 'name', 'groupNumbermber', function() {
  //   return `${this.get('serialNumber')} ${this.get('name')} ${this.get('groupNumber')}`;
  // })
});
