import axios from 'axios'


const Score_REST_API_URL = 'http://localhost:4001/scores'

class ScoreService {

    getScores() {
        return axios.get(Score_REST_API_URL);
    }

    postScore(props) {
        return axios.post(Score_REST_API_URL, props.score, { headers: { "Content-Type": "application/json" } });
    }


    putScore(props) {
        return axios.put('http://localhost:4001/scores/' + props.frame, props.score, { headers: { "Content-Type": "application/json" } });
    }

}

export default new ScoreService();
