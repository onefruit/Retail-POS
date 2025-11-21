import React from "react";

const UserForm = () => {
  return (
    <div className="mx-2 mt-2">
      <div className="card col-md-8 form-container">
        <div className="card-body">
          <form>
            <div className="mb-3">
              <label htmlFor="name" className="fomr-label">
                Name
              </label>
              <input
                type="text"
                name="name"
                id="name"
                placeholder="Prabin Shah"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="email" className="fomr-label">
                Email
              </label>
              <input
                type="email"
                name="email"
                id="email"
                placeholder="prabin@gmail.com"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="password" className="fomr-label">
                Password
              </label>
              <input
                type="password"
                name="password"
                id="password"
                placeholder="***************"
                className="form-control"
              />
            </div>

            <button type="submit" className="btn btn-primary w-100">
              Save
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default UserForm;
