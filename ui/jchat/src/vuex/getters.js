
export const getUser = state => state.user;

export const getCurrentSession = state => state.sessions.find(session => session.id === state.currentSessionId);


