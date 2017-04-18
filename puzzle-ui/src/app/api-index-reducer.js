import {types} from './api-index-actions';

const defaultState = {
  createUrl: null
};

export default function apiIndexReducer(state = defaultState, action) {
  switch (action.type) {
    case types.FETCH_API_INDEX + '_FULFILLED': {
      let createUrl = action.payload.data._links
        .find(link => link.rel === 'new-puzzle')
        .href;
      return Object.assign({}, state, {
        createUrl: createUrl
      });
    }
    default:
      return state;
  }
}

