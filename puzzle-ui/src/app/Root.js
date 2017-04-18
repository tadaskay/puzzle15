import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Provider} from 'react-redux';
import App from './App';
import PuzzleCreator from '../puzzle/PuzzleCreator';
import Board from '../puzzle/Board';
import Keys from '../puzzle/Keys';

export default class Root extends Component {
  render() {
    const {store} = this.props;
    return (
      <Provider store={store}>
        <App>
          <PuzzleCreator/>
          <Board/>
          <Keys/>
        </App>
      </Provider>
    );
  }
}

Root.propTypes = {
  store: PropTypes.object.isRequired,
};
