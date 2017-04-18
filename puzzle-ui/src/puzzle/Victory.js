import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

const propTypes = {
  complete: PropTypes.boolean
};

class Victory extends React.Component {

  render() {
    return (
      <div id="victory">
        {this.props.complete &&
        <div className="success">
          <p>Puzzle is complete!*</p>
          <span className="small">*But you can continue playing</span>
        </div>
        }
      </div>
    );
  }
}

Victory.propTypes = propTypes;

const mapStateToProps = (state) => ({
  complete: state.puzzle.complete
});

export default connect(mapStateToProps)(Victory);
