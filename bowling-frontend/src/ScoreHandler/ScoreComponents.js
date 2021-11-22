import React from 'react';
import ScoreService from './ScoreService';
import Dialog from "@material-ui/core/Dialog";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import Button from "@material-ui/core/Button";


class ScoreComponents extends React.Component {
    constructor(props) {
       
        super(props);
        this.state = {
            scores: [],
            totalScores: [],
            frame: '',
            firstScore: 0,
            secondScore: 0,
            max: 10,
            validfirstScore: true,
            open: false,
            lastFrame: false,



        }


    


        // localStorage.removeItem('frameNumber');


        // window.addEventListener("beforeunload", () => localStorage.removeItem('frameNumber'));

        // const [count, setCount] = React.useState(0);
        // this.handleChange = this.handleChange.bind(this);
        //  localStorage.setItem('frameNumber', JSON.stringify(0));
    }

    handleToClose() {
        this.setState({ open: false })
    };

    getAllScores() {
        ScoreService.getScores().then((Res) => {
            this.setState({ scores: Res.data })

        });

        ScoreService.getTotalScores().then((Res) => {
            this.setState({ totalScores: Res.data })
        });
    }

    componentDidMount() {
        this.getAllScores();
        //ScoreService.getScores();
    }



    handleChange = event => {
        event.preventDefault();

        this.setState({ [event.target.name]: event.target.value })
        if (event.target.name == "firstScore") {
          //  console.log("first Score!!!!!!!!!!!: " + parseInt(this.state.firstScore));
            if (parseInt(this.state.firstScore) === 9) {
                this.setState({ max: 0 });
            } else {
                this.setState({ max: 9 - parseInt(this.state.firstScore) });
            }
            this.setState({ validfirstScore: false })
        }

    }

    handleSubmit(frame) {
        // console.log("here ",this.state.scores[frame - 1] );
        /* if (frame == 10 || !localStorage.getItem('frameNumber')) {
             this.setState({ open: true })
         } */


        this.setState({ validfirstScore: true })


        //  console.log("added")
        //    console.log("frame: " + frame + "   firstScore: " + this.state.firstScore + "    secondScore: " + this.state.secondScore)

        console.log("scorreees: " + this.state.scores[frame - 1].scoreAdded );
      //  if (!this.state.scores[frame - 1].scoreAdded) { }
            let score = JSON.stringify({ "frame": frame, "firstScore": this.state.firstScore, "secondScore": this.state.secondScore, "scoreAdded": true })
            console.log(score);
            ScoreService.putScore(score = { score }, frame = { frame });
            localStorage.setItem('lockFrame', false);

            this.getAllScores();
        
        //  console.log("birch add: " + score.scoreAdded)
        //JSON.stringify(frame).split(":").[1]
        console.log("herrreee: " + this.state.lastFrame);
        if (this.state.lastFrame) {
            console.log("We are in!");
            this.setState({ open: true });
        }

        if (localStorage.getItem('frameNumber') == 11) {

            if (parseInt(this.state.firstScore) + parseInt(this.state.secondScore) == 10) {
               // console.log("here!!!" + this.open);
                let score = JSON.stringify({ "frame": 11, "firstScore": 0, "secondScore": 0, "scoreAdded": false })
                ScoreService.postScore(score = { score }).then((Res) => {
                //    console.log(Res);
                    this.getAllScores();
                    this.setState({lastFrame: true})
                });
                

            } else {
                this.setState({ open: true })
            }
          
            
            localStorage.removeItem('frameNumber');
            localStorage.setItem('disable', true);
        }

        //window.location.reload(false);

    }

    handleAddingFrame() {

        if (!JSON.parse(localStorage.getItem('frameNumber'))) {
            localStorage.setItem('frameNumber', JSON.stringify(1));
        }

       // console.log("added: " + this.state.frameNumber + " selected: " + JSON.parse(localStorage.getItem('frameNumber')));
        if (JSON.parse(localStorage.getItem('frameNumber')) <= 10) {
            let score = JSON.stringify({ "frame": JSON.parse(localStorage.getItem('frameNumber')), "firstScore": 0, "secondScore": 0, "scoreAdded": false })
            ScoreService.postScore(score = { score }).then((Res) => {
                console.log(Res);
                this.getAllScores();
                // window.location.reload(false);
            })
            localStorage.setItem('lockFrame', true);

            var temp = JSON.parse(localStorage.getItem('frameNumber'));

            localStorage.setItem('frameNumber', JSON.stringify(++temp));

            console.log("new local: " + JSON.parse(localStorage.getItem('frameNumber')));

        }


    }








    render() {
        return (
            <div>
                <button disabled={JSON.parse(localStorage.getItem('disable')) || JSON.parse(localStorage.getItem('lockFrame'))} className="btn btn-primary active" onClick={() => this.handleAddingFrame()} type="submit">Add frame</button>

                <h1 className="text-center">Score List</h1>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Frame</th>
                            <th>First score</th>
                            <th>Second Score</th>
                            <th></th>

                            <th>Total score</th>

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
                                            <button disabled={score.scoreAdded} className="btn btn-success" onClick={() => this.handleSubmit(score.frame)} type="submit">Add</button>
                                        </td>

                                        <td>{this.state.totalScores[score.frame-1]}</td>
                                       

                                    </tr>

                            )
                        }

                    </tbody>
                </table>


                <div>
                    <Dialog  open={this.state.open} onClick={() => this.handleToClose()} >
                        <DialogTitle>{"Total score"}</DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                {this.state.totalScores[9]}
          </DialogContentText>
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={() => this.handleToClose()}
                                color="primary" autoFocus>
                                Close
          </Button>
                        </DialogActions>
                    </Dialog>
                </div>

            </div>
        )
    }

}

export default ScoreComponents





