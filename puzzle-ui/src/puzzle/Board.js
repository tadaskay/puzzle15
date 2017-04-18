import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

const propTypes = {
  size: PropTypes.number,
  tiles: PropTypes.arrayOf(
    PropTypes.arrayOf(PropTypes.number)
  )
};

class Board extends React.Component {

  render() {
    if (!this.props.size) {
      return (<div className="board empty"/>)
    }
    let tileSize = 100 / this.props.size;
    let tileStyle = {
      flex: `1 0 ${tileSize}%`,
      height: `${tileSize}%`,

    };
    return (
      <div className="board">
        {this.props.tiles.map((row) => {
          return row.map((number) => {
            let blank = number === 0;
            let tileClass = `tile ${blank ? 'blank' : ''}`;
            return (
              <div key={number} style={tileStyle} className={tileClass}>
                <div className='number'>{number !== 0 ? number : ''}</div>
              </div>);
          });
        })}
      </div>
    )
      ;
  }
}

Board.propTypes = propTypes;

const mapStateToProps = (state) => {
  let {size, tiles} = state.puzzle;
  return {
    size: size,
    tiles: tiles
  };
};

export default connect(mapStateToProps)(Board);
