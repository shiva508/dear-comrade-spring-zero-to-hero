import { Route, Routes } from "react-router-dom";
import BookLayout from "../BookLayout";
import Book from "./Book";
import Booklist from "./Booklist";
import Newbook from "./Newbook";

const Bookrouts = () => {
  return (
    <>
      <Routes>
        <Route element={<BookLayout />}>
          <Route index element={<Booklist />} />
          <Route path=":id" element={<Book />} />
          <Route path="new" element={<Newbook />} />
        </Route>
      </Routes>
    </>
  );
};
export default Bookrouts;
