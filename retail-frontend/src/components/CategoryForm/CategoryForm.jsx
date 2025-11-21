import React from "react";

const CategoryForm = () => {
  return (
    <div className="mx-2 mt-2">
      <div className="card col-md-8 form-container">
        <div className="card-body">
          <form>
            <div className="mb-3">
              <label htmlFor="image" className="form-label">
                <img src="https://placehold.co/48x48" alt="" width={48} />
              </label>
              <input
                type="file"
                name="image"
                id="image"
                className="form-control"
                hidden
              />
            </div>

            <div className="mb-3">
              <label htmlFor="name" className="fomr-label">
                Name
              </label>
              <input
                type="text"
                name="name"
                id="name"
                placeholder="Category Name"
                className="form-control"
              />
            </div>


            
            <div className="mb-3">
              <label htmlFor="name" className="fomr-label">
                Description
              </label>
              <textarea
                rows="5"
                name="description"
                id="description"
                placeholder="Write content here..."
                className="form-control"
              />
            </div>
            
            <div className="mb-3">
                <label htmlFor="bgcolor" className="form-label">Background Color</label>
                <br />
                <input type="color" name="bgColor"
                id="bgColor"
                placeholder="#ffffff" />
            </div>

            <button type="submit" className="btn btn-primary w-100">Save</button>

          </form>
        </div>
      </div>
    </div>
  );
};

export default CategoryForm;
