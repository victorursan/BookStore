import DS from "ember-data";

// define URL prefix
export default DS.RESTAdapter.extend({
  namespace: 'myapp/api',
  // request headers
  headers: {
    'Accept': 'application/vnd.api+json',
    'Content-Type': 'application/vnd.api+json'
  }
});
