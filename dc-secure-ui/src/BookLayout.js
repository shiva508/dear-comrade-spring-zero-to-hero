import { Link, Outlet } from "react-router-dom";

const BookLayout = () => {
  return (
    <>
      <Link to="/books/408">Book 1</Link>
      <br />
      <Link to="/books/508">Book 2</Link>
      <br />
      <Link to="/books/new">New Book</Link>
      <Outlet context={{ hello: "world" }} />
    </>
  );
};
export default BookLayout;
