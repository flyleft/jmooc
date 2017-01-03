import * as types from './mutation-types'
export default {
  [types.INIT_DATA](state){
    let data = localStorage.getItem('jchat-session');
    if (data) {
        state.sessions = JSON.parse(data);
    }
  },
  [types.SEND_MESSAGE]({ sessions, currentSessionId }, content){
    let session = sessions.find(item => item.id === currentSessionId);
    session.messages.push({
        content: content,
        date: new Date(),
        self: true
    });
  },
  [types.SELECT_SESSION](state, id){
   state.currentSessionId = id;
  },
   [types.SET_FILTER_KEY] (state, value) {
       state.filterKey = value;
   }
}
