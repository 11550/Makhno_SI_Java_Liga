CREATE TABLE IF NOT EXISTS Friendships (
  id SERIAL PRIMARY KEY,
  user_id UUID,
  friend_id UUID,
  CONSTRAINT to_user_fk FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT to_friend_fk FOREIGN KEY (friend_id) REFERENCES users(id)
);