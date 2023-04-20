import React from "react"
import { featured } from "../data/Data"

const FeaturedCard = () => {
  return (
    <>
      <div className='row'>
        {featured.map((items, index) => (
          <div className='col-md' key={index}>
            <img src={items.cover} alt='' />
            <h4>{items.name}</h4>
            <label>{items.total}</label>
          </div>
        ))}
      </div>
    </>
  )
}

export default FeaturedCard
