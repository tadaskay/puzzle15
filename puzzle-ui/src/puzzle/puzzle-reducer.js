import {types} from './puzzle-actions';

const defaultState = {
  size: 0,
  tiles: [[]],
  complete: false,
  links: []
};

export default function puzzleReducer(state = defaultState, action) {
  switch (action.type) {
    case types.CREATE_NEW_PUZZLE + '_FULFILLED': {
      let puzzle = action.payload.data;
      let nonSelfLink = link => link.rel !== 'self';
      return Object.assign({}, state, {
        size: puzzle.size,
        complete: puzzle.complete,
        tiles: puzzle.tiles,
        links: puzzle._links.filter(nonSelfLink)
      });
    }
    default:
      return state;
  }
}
