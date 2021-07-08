import React from 'react';
import { InputNumber } from 'rsuite';
import './Box.css';

var max2;

function onChange(value) {
    console.log('changed', value);
    max2 = 10 - value;
    console.log('max2', max2);


}

function onChange2(value2) {
    console.log('changed2', value2);
    console.log('max2', max2);
}



class Boxes extends React.Component {
    constructor(props) {
        super(props);
        this.state = { value: '' };
        this.state = { value2: '' };
    }




    render() {
        return (
            <div>


                <p className="frame">Frame {this.props.number}: </p>

                <InputNumber className="input"

                    value={this.state.value}
                    step={1}
                    min={0}
                    max={10}
                    type='number'
                    style={{
                        width: 150,

                    }}
                    onChange={onChange}
                />




                <InputNumber className="input"

                    value2={this.state.value2}
                    step={1}
                    min={0}
                    max={3}
                    type='number'
                    style={{ width: 150 }}
                    onChange={onChange2}
                />





            </div>
        )
    }
}

export default Boxes