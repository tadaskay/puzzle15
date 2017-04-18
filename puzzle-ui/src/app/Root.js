import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Provider} from 'react-redux';
import App from './App';
import HomePage from './HomePage';
import PuzzleCreator from '../puzzle/PuzzleCreator';

export default class Root extends Component {
  render() {
    const {store} = this.props;
    return (
      <Provider store={store}>
        <App>
          <PuzzleCreator/>
          <HomePage/>
        </App>
      </Provider>
    );
  }
}

Root.propTypes = {
  store: PropTypes.object.isRequired,
};
