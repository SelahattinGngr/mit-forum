import { useCallback, useEffect, useState } from "react";
import { loadUsers } from "./api";

export function UserList() {
  const [userPage, setUserPage] = useState({
    content: [],
    last: false,
    first: false,
    number: 0,
  });

  const getUsers = useCallback(async (page) => {
    const response = await loadUsers(page);
    setUserPage(response.data);
  });

  useEffect(() => {
    getUsers();
  }, []);

  return (
    <>
      <div>user list</div>
      {userPage.map((user) => {
        return <div key={user.id}>{user.username}</div>;
      })}
      {!userPage.first && (
        <button onClick={() => getUsers(userPage.number - 1)}>Previous</button>
      )}
      {!userPage.last && (
        <button onClick={() => getUsers(userPage.number + 1)}>Next</button>
      )}
    </>
  );
}
