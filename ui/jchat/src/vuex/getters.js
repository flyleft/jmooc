
export const getUser = state => state.user;

export const getCurrentSession = state => state.sessions.find(session => session.id === state.currentSessionId);

export const getFilterSessions= state => state.sessions.filter(session => session.user.name.includes(state.filterKey));

export const getCurrentSessionId = state => state.currentSessionId;
