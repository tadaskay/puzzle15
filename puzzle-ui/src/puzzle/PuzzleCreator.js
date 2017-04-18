import React from 'react';
import PropTypes from 'prop-types';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {actions} from './puzzle-actions';

const propTypes = {
  actions: PropTypes.objectOf(PropTypes.func.isRequired).isRequired
};

class PuzzleCreator extends React.Component {

  createNewPuzzle() {
    this.props.actions.createNewPuzzle(this.props.newPuzzleUri);
  }

  render() {
    return (
      <div>
        <button onClick={() => this.createNewPuzzle()} type="button">New puzzle</button>
      </div>
    );
  }
}

PuzzleCreator.propTypes = propTypes;

const mapStateToProps = (state) => ({
  newPuzzleUri: state.apiIndex.newPuzzleUri
});
const mapDispatchToProps = (dispatch) => ({
  actions: bindActionCreators(actions, dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(PuzzleCreator);
