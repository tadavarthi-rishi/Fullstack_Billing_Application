const CategoryForm = () => {
    return (
        <div className="mx-2 mt-2">
            <div className="row">
                <div className="card col-md-8 form-container">
                    <div className = "card-body">
                        <form>
                            <div className="mb-3">
                                <label htmlFor="image" className="form-label">
                                    <img src="https://placehold.co/48x48" alt="" width={48}/>
                                </label>
                                <input type="file" name="image" id="image" className='form-control' hidden />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CategoryForm;