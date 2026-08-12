// // import { useState } from 'react'
// // import reactLogo from './assets/react.svg'
// // import viteLogo from './assets/vite.svg'
// // import heroImg from './assets/hero.png'
// // import './App.css'

// // function App() {
// //   const [count, setCount] = useState(0)

// //   return (
// //     <>
// //       <section id="center">
// //         <div className="hero">
// //           <img src={heroImg} className="base" width="170" height="179" alt="" />
// //           <img src={reactLogo} className="framework" alt="React logo" />
// //           <img src={viteLogo} className="vite" alt="Vite logo" />
// //         </div>
// //         <div>
// //           <h1>Get started</h1>
// //           <p>
// //             Edit <code>src/App.jsx</code> and save to test <code>HMR</code>
// //           </p>
// //         </div>
// //         <button
// //           type="button"
// //           className="counter"
// //           onClick={() => setCount((count) => count + 1)}
// //         >
// //           Count is {count}
// //         </button>
// //       </section>

// //       <div className="ticks"></div>

// //       <section id="next-steps">
// //         <div id="docs">
// //           <svg className="icon" role="presentation" aria-hidden="true">
// //             <use href="/icons.svg#documentation-icon"></use>
// //           </svg>
// //           <h2>Documentation</h2>
// //           <p>Your questions, answered</p>
// //           <ul>
// //             <li>
// //               <a href="https://vite.dev/" target="_blank">
// //                 <img className="logo" src={viteLogo} alt="" />
// //                 Explore Vite
// //               </a>
// //             </li>
// //             <li>
// //               <a href="https://react.dev/" target="_blank">
// //                 <img className="button-icon" src={reactLogo} alt="" />
// //                 Learn more
// //               </a>
// //             </li>
// //           </ul>
// //         </div>
// //         <div id="social">
// //           <svg className="icon" role="presentation" aria-hidden="true">
// //             <use href="/icons.svg#social-icon"></use>
// //           </svg>
// //           <h2>Connect with us</h2>
// //           <p>Join the Vite community</p>
// //           <ul>
// //             <li>
// //               <a href="https://github.com/vitejs/vite" target="_blank">
// //                 <svg
// //                   className="button-icon"
// //                   role="presentation"
// //                   aria-hidden="true"
// //                 >
// //                   <use href="/icons.svg#github-icon"></use>
// //                 </svg>
// //                 GitHub
// //               </a>
// //             </li>
// //             <li>
// //               <a href="https://chat.vite.dev/" target="_blank">
// //                 <svg
// //                   className="button-icon"
// //                   role="presentation"
// //                   aria-hidden="true"
// //                 >
// //                   <use href="/icons.svg#discord-icon"></use>
// //                 </svg>
// //                 Discord
// //               </a>
// //             </li>
// //             <li>
// //               <a href="https://x.com/vite_js" target="_blank">
// //                 <svg
// //                   className="button-icon"
// //                   role="presentation"
// //                   aria-hidden="true"
// //                 >
// //                   <use href="/icons.svg#x-icon"></use>
// //                 </svg>
// //                 X.com
// //               </a>
// //             </li>
// //             <li>
// //               <a href="https://bsky.app/profile/vite.dev" target="_blank">
// //                 <svg
// //                   className="button-icon"
// //                   role="presentation"
// //                   aria-hidden="true"
// //                 >
// //                   <use href="/icons.svg#bluesky-icon"></use>
// //                 </svg>
// //                 Bluesky
// //               </a>
// //             </li>
// //           </ul>
// //         </div>
// //       </section>

// //       <div className="ticks"></div>
// //       <section id="spacer"></section>
// //     </>
// //   )
// // }

// // export default App
// import { useEffect, useState } from "react";
// import { getDocuments, addDocument } from "./api/documentApi";

// function App() {
//   const [docs, setDocs] = useState([]);

//   useEffect(() => {
//     getDocuments().then(setDocs);
//   }, []);

//   const handleAdd = () => {
//     addDocument({ name: "New Document" }).then(() => {
//       getDocuments().then(setDocs); // refresh list
//     });
//   };

//   return (
//     <div>
//       <h1>Documents</h1>
//       <ul>
//         {docs.map((doc) => (
//           <li key={doc.id}>{doc.name}</li>
//         ))}
//       </ul>
//       <button onClick={handleAdd}>Add Document</button>
//     </div>
//   );
// }

// export default App;

import { useEffect, useState } from "react";
import { getDocuments, addDocument, updateDocument, deleteDocument } from "./api/documentApi";

function App() {
  const [docs, setDocs] = useState([]);
  const [newName, setNewName] = useState("");
  const [editId, setEditId] = useState(null);
  const [editName, setEditName] = useState("");

  // Load documents
  useEffect(() => {
    refreshDocs();
  }, []);

  const refreshDocs = () => {
    getDocuments().then(setDocs);
  };

  // Add document
  const handleAdd = () => {
    if (!newName) return;
    addDocument({ name: newName }).then(() => {
      setNewName("");
      refreshDocs();
    });
  };

  // Update document
  const handleUpdate = (id) => {
    if (!editName) return;
    updateDocument(id, { name: editName }).then(() => {
      setEditId(null);
      setEditName("");
      refreshDocs();
    });
  };

  // Delete document
  const handleDelete = (id) => {
    deleteDocument(id).then(() => refreshDocs());
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Documents</h1>

      {/* Add new document */}
      <div>
        <input
          type="text"
          placeholder="New document name"
          value={newName}
          onChange={(e) => setNewName(e.target.value)}
        />
        <button onClick={handleAdd}>Add</button>
      </div>

      {/* List documents */}
      <ul>
        {docs.map((doc) => (
          <li key={doc.id}>
            {editId === doc.id ? (
              <>
                <input
                  type="text"
                  value={editName}
                  onChange={(e) => setEditName(e.target.value)}
                />
                <button onClick={() => handleUpdate(doc.id)}>Save</button>
                <button onClick={() => setEditId(null)}>Cancel</button>
              </>
            ) : (
              <>
                {doc.name}
                <button onClick={() => { setEditId(doc.id); setEditName(doc.name); }}>Edit</button>
                <button onClick={() => handleDelete(doc.id)}>Delete</button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
