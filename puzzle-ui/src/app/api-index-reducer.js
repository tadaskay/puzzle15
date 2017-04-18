import {types} from './api-index-actions';

const defaultState = {
  newPuzzleUri: null
};

export default function apiIndexReducer(state = defaultState, action) {
  switch (action.type) {
    case types.FETCH_API_INDEX + '_FULFILLED': {
      let uri = action.payload.data._links
        .find(link => link.rel === 'new-puzzle')
        .href;
      return Object.assign({}, state, {
        newPuzzleUri: uri
      });
    }
    default:
      return state;
  }
}

