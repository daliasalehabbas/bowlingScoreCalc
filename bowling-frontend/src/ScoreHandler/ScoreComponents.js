import React from 'react';
import ScoreService from './ScoreService';


class ScoreComponents extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			scores:[],
			frame: '',
			firstScore: 0,
            secondScore: 0,
            max: 10, 
            validfirstScore: true,
           
            

        }

        if (localStorage.getItem('myData') == 11) {
            localStorage.removeItem('myData');
            localStorage.setItem('disable', true);
        }

       // localStorage.removeItem('myData');
    

       // window.addEventListener("beforeunload", () => localStorage.removeItem('myData'));
        
       // const [count, setCount] = React.useState(0);
        // this.handleChange = this.handleChange.bind(this);
      //  localStorage.setItem('myData', JSON.stringify(0));
    }



    handleChange = event => {
        event.preventDefault();

        this.setState({ [event.target.name]: event.target.value })
        if (event.target.name === "firstScore") {
            this.setState({ max: 9 - parseInt(this.state.firstScore) });
            this.setState({ validfirstScore:false })
        }
       
    }

    handleSubmit(frame) {
        
        this.setState({ validfirstScore: true })
        const f = this.state.firstScore
        const s = this.state.secondScore
        
        console.log(parseInt(f) + parseInt(s))
        console.log("added")
        console.log("frame: " + frame + "firstScore"+this.state.firstScore + "secondScore"+ this.state.secondScore)
        
        let score = JSON.stringify({ "frame": frame, "firstScore": this.state.firstScore, "secondScore": this.state.secondScore })

        ScoreService.putScore(score = { score }, frame = { frame });
        localStorage.setItem('lockFrame', false);

        window.location.reload(false);

    }

    handleAdding() {
       
        if (!JSON.parse(localStorage.getItem('myData'))){
            localStorage.setItem('myData', JSON.stringify(1));
        }

        console.log("added: " + this.state.frameNumber + " selected: " + JSON.parse(localStorage.getItem('myData')));
        if (JSON.parse(localStorage.getItem('myData')) <= 10) {
            let score = JSON.stringify({ "frame": JSON.parse( localStorage.getItem('myData')), "firstScore": 0, "secondScore": 0 })
            ScoreService.postScore(score = { score }).then((Res) => {
                console.log(Res);
                window.location.reload(false);
            })
            localStorage.setItem('lockFrame', true);

            var temp = JSON.parse(localStorage.getItem('myData'));

            localStorage.setItem('myData', JSON.stringify(++temp));

            console.log("new local: " + JSON.parse(localStorage.getItem('myData')));

        }

        
    }



    componentDidMount() {
       
      
        ScoreService.getScores().then((Res) => {
            this.setState({ scores: Res.data })
            
        });
    }

   
   

	render() {
		return (
            <div>
                <button disabled={ JSON.parse(localStorage.getItem('disable')) && JSON.parse(localStorage.getItem('lockFrame'))} className="btn btn-primary active" onClick={() => this.handleAdding()} type="submit">Add frame</button>

                <h1 className="text-center">Score List</h1>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Frame</th>
                            <th>First score</th>
                            <th>Second Score</th>
                            <th></th>
                           
                            <th>total Score</th>

                        </tr>
                    </thead>

                    <tbody>
                        {

                            this.state.scores.map(
                                (score) =>
                                    <tr>

                                        <td>{score.frame}</td>

                                        <td><input placeholder={score.firstScore} onKeyDown={(event) => { event.preventDefault(); }} type="number" min={0} max={10} name="firstScore" onChange={this.handleChange} /></td>
                                        <td><input placeholder={score.secondScore} disabled={this.state.validfirstScore} onKeyDown={(event) => { event.preventDefault(); }} type="number" min={0} max={this.state.max} name="secondScore" onChange={this.handleChange} /></td>
                                        <td>
                                            <button  className="btn btn-success" onClick={() => this.handleSubmit(score.frame)} type="submit">Add</button>
                                        </td>
                                     
                                        <td>{score.totalScore}</td>
                                    </tr>
                            )
                        }
                    
                    </tbody>
                </table>
			</div>
			)
	}

}

export default ScoreComponents